function displaychart(data) {
    chart = new Highcharts.Chart({
        chart: {
            renderTo: 'myChart',
            type: 'column',
            options3d: {
                enabled: true,
                alpha: 0,
                beta: 0,
                depth: 70,
                viewDistance: 25
            }
        },
        xAxis: {
            categories:data.dayDate
        },
        yAxis: {
            title: {
                enabled: false
            }
        },
        tooltip: {
            headerFormat: '<b>{point.key}</b><br>',
            pointFormat: 'Cars sold: {point.y}'
        },
        title: {
            text: '2023년 1분기 가격 ',
            align: 'left'
        },
        subtitle: {
            text: 'Source: ' +
                '<a href="https://ofv.no/registreringsstatistikk"' +
                'target="_blank">OFV</a>',
            align: 'left'
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            column: {
                depth: 25
            }
        },
        series :[{
            data:data.dayTotal
        }]
    })
}






