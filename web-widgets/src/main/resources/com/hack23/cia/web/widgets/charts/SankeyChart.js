function com_hack23_cia_web_widgets_charts_SankeyChart() {
    var connector;

    function init() {
        google.charts.load('current', {'packages':['sankey']});
        google.charts.setOnLoadCallback(this.ready);
    }

    this.ready = function() {
        connector.loaded = true;
        connector.draw();
    }

    this.doDraw = function() {
        // no direct draws:
        // need to check that charts are loaded before
        if (connector.loaded) {
            connector.draw();
        } else {
            google.charts.setOnLoadCallback(this.ready);
        }
    }

    this.draw = function () {
		var data = new google.visualization.DataTable();
        data.addColumn('string', 'From');
        data.addColumn('string', 'To');
        data.addColumn('number', 'Total');

        var dataValueList = this.getState().values;
        var rowData =  Array.from({length: dataValueList.length});

         for (var index = 0; index < dataValueList.length; index++ ) {
            var tempData =  Array.from({length: 3});
            tempData[0] = dataValueList[index][0];
            tempData[1] = dataValueList[index][1];
            tempData[2] = dataValueList[index][2];
            rowData[index] = tempData;
        }

		data.addRows(rowData);

        var options = {
          width: '100%',
          sankey: {
              link: {
                colorMode: 'gradient'
              }
            }
        };

        var chart = new google.visualization.Sankey(document.getElementById(this.getState().myId));
        chart.draw(data, options);
    }

    connector = this;

    init();
}
