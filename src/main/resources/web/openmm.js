// http://stackoverflow.com/questions/4660934/how-does-one-disable-caching-in-jquery-mobile-ui
// Martin's solution
$(document).on('pagehide', function (e) {
    var page = $(e.target);
    if (!$.mobile.page.prototype.options.domCache
        && (!page.attr('data-dom-cache')
            || page.attr('data-dom-cache') == "false")
        ) {
        page.remove();
    }
});