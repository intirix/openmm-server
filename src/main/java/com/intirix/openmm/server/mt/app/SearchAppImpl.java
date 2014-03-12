package com.intirix.openmm.server.mt.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.api.beans.SearchResult;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;

public class SearchAppImpl implements SearchApp
{
	private final Log log = LogFactory.getLog( SearchAppImpl.class );

	private final String index;

	private MovieApp movieApp;

	private ShowApp showApp;

	public SearchAppImpl( String indexFile )
	{
		this.index = indexFile;
	}



	public MovieApp getMovieApp()
	{
		return movieApp;
	}



	public void setMovieApp( MovieApp movieApp )
	{
		this.movieApp = movieApp;
	}



	public ShowApp getShowApp()
	{
		return showApp;
	}



	public void setShowApp( ShowApp showApp )
	{
		this.showApp = showApp;
	}



	public void reindex() throws OpenMMMidtierException
	{
		final Analyzer analyzer = new StandardAnalyzer( Version.LUCENE_46 );
		try
		{
			final File indexFile = new File( index );
			if ( !indexFile.exists() )
			{
				indexFile.mkdirs();
			}
			final Directory dir = FSDirectory.open( indexFile );
			final IndexWriterConfig config = new IndexWriterConfig( Version.LUCENE_46, analyzer );
			config.setOpenMode( OpenMode.CREATE_OR_APPEND );
			
			final IndexWriter iwriter = new IndexWriter( dir, config );

			for ( final Movie movie: movieApp.listMovies() )
			{
				final Document doc = new Document();
				doc.add( new Field( "type", "movie", TextField.TYPE_STORED ) );
				doc.add( new Field( "title", movie.getName(), TextField.TYPE_STORED ) );
				doc.add( new Field( "contents", movie.getName() + " " + movie.getDescription(), TextField.TYPE_STORED ) );
				doc.add( new Field( "description", movie.getDescription(), TextField.TYPE_STORED ) );
				doc.add( new Field( "refId", "" + movie.getId(), TextField.TYPE_STORED ) );
				doc.add( new StringField( "uid", "movie:" + movie.getId(), Field.Store.YES ) );
				iwriter.updateDocument( new Term( "uid", "movie:" + movie.getId() ), doc );
			}
			
			for ( final Show show: showApp.listShows() )
			{
				final Document doca = new Document();
				doca.add( new Field( "type", "show", TextField.TYPE_STORED ) );
				doca.add( new Field( "title", show.getName(), TextField.TYPE_STORED ) );
				doca.add( new Field( "contents", show.getName() + " " + show.getDescription(), TextField.TYPE_STORED ) );
				doca.add( new Field( "description", show.getDescription(), TextField.TYPE_STORED ) );
				doca.add( new Field( "refId", "" + show.getId(), TextField.TYPE_STORED ) );
				doca.add( new StringField( "uid", "show:" + show.getId(), Field.Store.YES ) );
				iwriter.updateDocument( new Term( "uid", "show:" + show.getId() ), doca );
				
				for ( final Season season: showApp.listSeasons( show.getId() ) )
				{
					for ( final Episode ep: showApp.listEpisodes( season.getId() ) )
					{
						final Document doc = new Document();
						doc.add( new Field( "type", "episode", TextField.TYPE_STORED ) );
						doc.add( new Field( "title", ep.getName(), TextField.TYPE_STORED ) );
						doc.add( new Field( "contents", ep.getName() + " " + ep.getDescription() + ' ' + ep.getGuests(), TextField.TYPE_STORED ) );
						doc.add( new Field( "description", show.getName() + " " + season.getNumber() + 'x' + ep.getEpNum() + " " + ep.getDescription(), TextField.TYPE_STORED ) );
						doc.add( new Field( "refId", "" + ep.getId(), TextField.TYPE_STORED ) );
						doc.add( new StringField( "uid", "epid:" + ep.getId(), Field.Store.YES ) );
						iwriter.updateDocument( new Term( "uid", "epid:" + ep.getId() ), doc );
					}
				}
				
			}
			
			iwriter.close();
			dir.close();

		}
		catch ( IOException e )
		{
			throw new OpenMMMidtierException(e);
		}
	}

	public List< SearchResult > search( String queryString ) throws OpenMMMidtierException
	{
		final List< SearchResult > results = new ArrayList< SearchResult >( 20 );
		final File indexFile = new File( index );
		if ( !indexFile.exists() )
		{
			indexFile.mkdirs();
		}
		try
		{
			final Analyzer analyzer = new StandardAnalyzer( Version.LUCENE_46 );
			final Directory dir = FSDirectory.open( indexFile );
			final DirectoryReader ireader = DirectoryReader.open(dir);
			final IndexSearcher isearcher = new IndexSearcher( ireader );
			
			
			log.debug( "Searching for [" + queryString + ']' );
			final QueryParser parser = new QueryParser( Version.LUCENE_46, "contents", analyzer );
			final Query query = parser.parse( queryString );
			final ScoreDoc[] hits = isearcher.search( query, null, 20 ).scoreDocs;
			for ( final ScoreDoc sd: hits )
			{
				final Document doc = isearcher.doc( sd.doc );
				
				final SearchResult result = new SearchResult();
				
				result.setDescription( doc.get( "description" ) );
				result.setTitle( doc.get( "title" ) );
				result.setType( doc.get( "type" ) );
				result.setRefId( Integer.parseInt( doc.get( "refId" ) ) );
				
				//log.debug( "    " + result.getTitle() );
				
				results.add( result );
			}
			ireader.close();
			dir.close();
		}
		catch ( IOException e )
		{
			throw new OpenMMMidtierException(e);
		}
		catch ( ParseException e )
		{
			throw new OpenMMMidtierException(e);
		}
		return results;
	}

}
