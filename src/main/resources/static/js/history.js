document.addEventListener('DOMContentLoaded', function () {
    const table = document.querySelector('.history-table');
    const headers = table.querySelectorAll('th.sortable');
    let isAsc = true;

    headers.forEach(header => {
        header.addEventListener('click', () => {
            const index = Array.from(header.parentElement.children).indexOf(header);
            const rows = Array.from(table.querySelectorAll('tbody tr'));

            isAsc = !isAsc;
            rows.sort((rowA, rowB) => {
                const cellA = rowA.children[index].textContent.trim();
                const cellB = rowB.children[index].textContent.trim();

                if (header.dataset.type === 'date') {
                    const dateA = new Date(cellA);
                    const dateB = new Date(cellB);
                    return isAsc ? dateA - dateB : dateB - dateA;
                } else {
                    return isAsc ? cellA.localeCompare(cellB) : cellB.localeCompare(cellA);
                }
            });

            rows.forEach(row => table.querySelector('tbody').appendChild(row));

            headers.forEach(th => th.classList.remove('sorted-asc', 'sorted-desc'));
            header.classList.add(isAsc ? 'sorted-asc' : 'sorted-desc');
        });
    });
});