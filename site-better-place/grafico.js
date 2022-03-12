function carregarGrafico(){
    const labelspiz = [
    'Argentina',
    'Brasil'
    ];
    valor = [200,400]
    const counter={
        id: "counter",
        beforeDraw( chart, args, options ){
            const { ctx, chartArea:{ top, bottom, right, left, width, height } }= chart;
            ctx.save();
            ctx.font = '60px sans-serif';
            ctx.textAlign = 'center';
            ctx.fillStyle = options.fontColor;
            ctx.fillText(((valor[0]*100)/valor[1]).toFixed(2)+"%", (width/2), (height/2)+20);
        }
    };

    const datapiz = {
        labels: labelspiz,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ['#6699ff', "transparent"],
            borderColor: ['#6699ff', "transparent"],
            borderRadius: 10,
            data: valor,
            cutout: "90%"
        }]
    };
    const configPiz = {
        type: 'doughnut',
        data: datapiz,
        options: {
            maintainAspectRatio: false,
            plugins: {
                tooltip:{
                    enabled : false
                },
                legend: {
                    display: false
                },
                counter:{
                    fontColor: 'blue'
                }
            }
        },
        plugins:[counter]
    };
    var myChartPiz = new Chart(
        document.getElementById('myChartPiz'),
        configPiz
    );
}






/*
    25 100
    20 x
    25x = 20*100
    x=(20*100)/25
*/