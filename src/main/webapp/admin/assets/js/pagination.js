function generatePageButtons(currPage, maxPages, hrefPrefix) {
    var lowerBound = currPage - 5;
    var upperBound = currPage + 5;

    if (lowerBound < 1) lowerBound = 1;
    if (upperBound > maxPages) upperBound = maxPages;

    if (currPage - lowerBound < 5) {
        upperBound = upperBound + 5 > maxPages ? maxPages : upperBound + 5;
    }

    if (upperBound - currPage < 5) {
        lowerBound = lowerBound - 5 < 1 ? 1 : lowerBound - 5;
    }

    var html = "";

    html += `<li class="page-item ${(lowerBound < currPage) ? "" : "disabled"}">
                <a class="page-link" href="${hrefPrefix}${currPage - 1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>`;

    vm.prependPages = [];
    vm.appendPages = [];

    for (var i = lowerBound; i < currPage; i++) {
        vm.prependPages.push(i);
        //html += `<li class="page-item"><a class="page-link" href="${hrefPrefix}${i}">${i}</a></li>`;
    }

    html += `<li class="page-item active"><a class="page-link" href="">${currPage}</a></li>`;

    for (var i = currPage + 1; i <= upperBound; i++) {
        vm.appendPages.push(i);
        //html += `<li class="page-item"><a class="page-link" href="${hrefPrefix}${i}">${i}</a></li>`;
    }

    html += `<li class="page-item ${(upperBound > currPage) ? "" : "disabled"}">
                <a class="page-link" href="${hrefPrefix}${currPage + 1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>`;
    return html;
}