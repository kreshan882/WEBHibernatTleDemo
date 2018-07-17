var GridRowCount = (function () {
    return{
        JqGridRows: function (_index,_grid) {
            
            var height = $('.d-col-widget').eq(_index).height();

            var jqRows_height = (height - 40);
            var rows_count = (jqRows_height / 28);
            var rows = Math.round(rows_count);
//            console.log(rows);
            GridRowCount.incrementRowNum(rows, _grid);
        },
        incrementRowNum: function (rows, _index) {
            var grid = $("#"+_index);

            grid.jqGrid('setGridParam',{rowNum: rows});
//            grid.jqGrid('setGridParam',{rowList: [20,30,50]});
            grid.trigger('reloadGrid');
        }

    };
})();


