var valorArrecadacao=0;
var myChartPiz;

function carregarGrafico(){
    const labelspiz = [];
    var counter={
        id: "counter",
        beforeDraw( chart, args, options ){
            const { ctx, chartArea:{ top, bottom, right, left, width, height } }= chart;
            ctx.save();
            ctx.font = '40px Comfortaa';
            ctx.textAlign = 'center';
            ctx.fillStyle = options.fontColor;
            ctx.fillText("R$"+options.fillText+",00", (width/2), (height/2)+20);
        }
    };

    var datapiz = {
        labels: labelspiz,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ['#01396F', "#EEEEEE"],
            borderColor: ['#01396F', "#EEEEEE"],
            data: calcularRestante(2000,valorArrecadacao),
            cutout: "90%"
        }]
    };
    var configPiz = {
        type: 'doughnut',
        data: datapiz,
        options: {
            maintainAspectRatio: false,
            plugins: {
                tooltip:{
                    enabled: false
                },
                legend: {
                    display: false
                },
                counter:{
                    fontColor: '#01396F',
                    fillText: calcularRestante(2000,valorArrecadacao)[0]
                }
            }
        },
        plugins:[counter]
    };
    myChartPiz = new Chart(
        document.getElementById('myChartPiz'),
        configPiz
    );
}

function calcularRestante(valorDesejado, valorAtual){
    if(valorAtual<=valorDesejado){
        return [valorAtual, valorDesejado-valorAtual];
    }
    else{
        return [valorAtual]
    }
    
}

function adicionarDinheiro(valor){
    valorArrecadacao+=valor;
    myChartPiz.data.datasets[0].data=calcularRestante(2000,valorArrecadacao);
    myChartPiz.config.options.plugins.counter.fillText= valorArrecadacao;
    myChartPiz.update();
}

function home(){
    window.location.href= "./home.html";
}

function adicionarValor(){
    valorArrecadacao+=Number(valor.value);
    myChartPiz.data.datasets[0].data=calcularRestante(2000,valorArrecadacao);
    myChartPiz.config.options.plugins.counter.fillText= valorArrecadacao;
    myChartPiz.update();
}


/*
    25 100
    20 x
    25x = 20*100
    x=(20*100)/25
*/