<template>
  <div class="aaaa">
    <el-row>
      <el-col :span="6" class="msa-col">

        <div class="msa">
<!--          <div class="grid-content bg-purple" id="myChart">-->
<!--            <img src="../assets/images/156156.png" width="100%" height="100%" >-->
<!--          </div>-->
          <div class="grid-content bg-purple" id="myChart1"></div>
        </div>
<!--        <div class="msa">-->
<!--          <div class="bode_main">-->
<!--            <img src="https://w.wallhaven.cc/full/vg/wallhaven-vg7lv3.jpg" alt="" class="tp_img" width="150px" height="150px">-->
<!--            <p>admin</p>-->
<!--            <p>超级管理员</p>-->
<!--          </div>-->

<!--        </div>-->

      </el-col>
      <el-col :span="18" class="msa-col">
        <div class="right_main">
          <div class="grid-content bg-purple" id="chartLineBox" style="display: block;width: 100%; height: 100%"></div>
        </div>
        <div class="right_main">
          <div class="total-layout">
            <el-row :gutter="20">
              <el-col :span="6">
                <div class="total-frame">
<!--                  <img src="../assets/images/home_today_amount.png" class="total-icon">-->
                  <div class="total-title">今日订单总数</div>
                  <div class="total-value">{{this.sellInfo[this.sellInfo.length-1].num}}</div>
                </div>
              </el-col>
              <el-col :span="6">
                <div class="total-frame">
<!--                  <img src="../assets/images/home_today_amount.png" class="total-icon">-->
                  <div class="total-title">今日销售总额</div>
                  <div class="total-value">{{this.sellInfo[this.sellInfo.length-1].amount}}</div>
                </div>
              </el-col>
              <el-col :span="6">
                <div class="total-frame">
<!--                  <img src="../assets/images/home_today_amount.png" class="total-icon">-->
                  <div class="total-title">昨日销售总额</div>
                  <div class="total-value">{{this.sellInfo[this.sellInfo.length-2].amount}}</div>
                </div>
              </el-col>
            </el-row>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
// 基于准备好的dom，初始化echarts实例
// import img_home_order from '@/assets/images/home_order.png';
// import img_home_today_amount from '@/assets/images/home_today_amount.png';
// import img_home_yesterday_amount from '@/assets/images/home_yesterday_amount.png';
import permission from '@/directive/permission/index.js'

export default {
  name:'',
  directives: { permission },
  data() {
    return {
      msg: 'Welcome to Your Vue.js App',
      sellInfo:[{
        amount: 100,
        num: 100,
        day: 1
      },{
        amount: 200,
        num: 200,
        day: 2
      },{
        amount: 300,
        num: 300,
        day: 3
      },{
        amount: 400,
        num: 400,
        day: 4
      },{
        amount: 500,
        num: 500,
        day: 5
      },{
        amount: 600,
        num: 600,
        day: 6
      },{
        amount: 700,
        num: 700,
        day: 7
      },{
        amount: 800,
        num: 800,
        day: 8
      }]
      }
  },
  created(){
    this.drawLine1();
    this.drawLine2();
  },
  mounted() {
    this.drawLine1();
    this.drawLine2();
  },
  methods: {
    drawLine(){

      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('myChart'))
      // 绘制图表
      myChart.setOption({
        title: { text: '在Vue中使用echarts' },
        tooltip: {},
        xAxis: {
          textStyle:{
            color:'#fff'
          },
          data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        textStyle:{
          color:'#fff'
        },
        yAxis: {},
        series: [{
          name: '销量',
          type: 'bar',
          data: [5, 20, 36, 10, 10, 20]
        }]
      });
    },
    drawLine1(){
      // this.$http.get('/mallGoods/shuliang').then(res => {
      //   let list = res.data;
      //   console.log(list,'0012');
      //   for(let i = 0;i < list.length;i++){
      //     if(i<10){
      //       let newValue = list[i].goodsSell;
      //       let newName = list[i].goodsName;
      //       newList.push({value:newValue,name:newName})
      //     }
      //   }
      //   console.log(newList,'0011112');
      let newList = [];
      newList.push({value:335, name:'衬衫'});
      newList.push({value:310, name:'羊毛衫'});
      newList.push({value:234, name:'雪纺衫'});
      newList.push({value:135, name:'裤子'});
      newList.push({value:1548, name:'高跟鞋'});
      newList.push({value:1548, name:'高跟鞋'});
      newList.push({value:1548, name:'高跟鞋'});
      newList.push({value:1548, name:'高跟鞋'});
      newList.push({value:1548, name:'高跟鞋'});
      newList.push({value:1548, name:'高跟鞋'});

        // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById('myChart1'))
        // 绘制图表
        myChart.setOption({
          title: {
            text: '商城数据',
            subtext: '销量排行',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: '0',
            top:'100',
            textStyle:{
              color:'#000'
            }
          },
          textStyle:{
            color:'#000'
          },
          series: [
            {
              name: '访问来源',
              type: 'pie',
              radius: '50%',
              data: newList,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        });


      // });

    },
    drawLine2(){
      let amount = []
      let num = []
      let day = []

      // this.$http.get('/mallGoodsOrder/getSellNumber').then(res =>
      //     {
      //       console.log('销售额')
      //       // console.log(res)
      //       let temp = res.data.data.dataList
      //       // console.log(temp)
      //       // let item = ''
      //       temp.forEach(item=>{
      //         amount.push(item.amount)
      //         num.push(item.num)
      //         day.push(item.day)
      //       })
      let temp =[{
        amount: 100,
        num: 100,
        day: 1
      },{
        amount: 200,
        num: 200,
        day: 2
      },{
        amount: 300,
        num: 300,
        day: 3
      },{
        amount: 400,
        num: 400,
        day: 4
      },{
        amount: 500,
        num: 500,
        day: 5
      },{
        amount: 600,
        num: 600,
        day: 6
      },{
        amount: 700,
        num: 700,
        day: 7
      },{
        amount: 800,
        num: 800,
        day: 8
      }]

      amount=[100,200,300,400,500,600,700,800]
      num = [1,2,3,4,5,6,7,8]
      day = [1,2,3,4,5,6,7,8]
            this.sellInfo = temp
            var option = {
              tooltip: {              //设置tip提示
                trigger: 'axis'
              },

              legend: {               //设置区分（哪条线属于什么）
                data:['总销量','总销售额']
              },
              color: ['#8AE09F', '#FA6F53'],       //设置区分（每条线是什么颜色，和 legend 一一对应）
              xAxis: {                //设置x轴
                type: 'category',
                boundaryGap: false,     //坐标轴两边不留白
                // data: ['2019-1-1', '2019-2-1', '2019-3-1', '2019-4-1', '2019-5-1', '2019-6-1', '2019-7-1',],
                data: day,
                name: 'DATE',           //X轴 name
                nameTextStyle: {        //坐标轴名称的文字样式
                  color: '#FA6F53',
                  fontSize: 16,
                  padding: [0, 0, 0, 20]
                },
                axisLine: {             //坐标轴轴线相关设置。
                  lineStyle: {
                    color: '#FA6F53',
                  }
                }
              },
              yAxis: {
                name: 'SALES VOLUME',
                nameTextStyle: {
                  color: '#FA6F53',
                  fontSize: 16,
                  padding: [0, 0, 10, 0]
                },
                axisLine: {
                  lineStyle: {
                    color: '#FA6F53',
                  }
                },
                type: 'value'
              },
              series: [
                {
                  name: '总销售额',
                  data:  amount,
                  type: 'line',               // 类型为折线图
                  lineStyle: {                // 线条样式 => 必须使用normal属性
                    normal: {
                      color: '#8AE09F',
                    }
                  },
                },
                {
                  name: '总销量',
                  data: num,
                  type: 'line',
                  lineStyle: {
                    normal: {
                      color: '#FA6F53',
                    }
                  },
                }
              ]
            };

            let charLine = this.$echarts.init(document.getElementById('chartLineBox'));


            // 指定图表的配置项和数据


            // 使用刚指定的配置项和数据显示图表。
            charLine.setOption(option);
      //     }
      // )




    },


  }
}
</script>
<style lang="less" scoped>
.left-map {
  /*width: 100%;*/
  /*height: 200px;*/
  /*margin-top: 20px;*/
  /*background-color: white;*/
}
.tp_img{
  border-radius: 50%;
  display: block;
  margin: auto;
  padding: 40px;
}
#myChart6{
  height: 90%;
}
#myChart {
  width:100%;
  height: 80%;
}
#myChart1 {
  height: 90%;
  width:100%;
}

.el-row {
  height: 100%;
}

.aaaa {
  height: 100%;
  overflow: hidden;
}

.msa-col {
  height: 100%;

}

.msa {
  height: 45%;
  background: #fff;
  padding-top: 30px;
  margin-bottom: 20px;
}
.right_main{
  height: 70%;
  width: 98%;
  margin-left: 20px;
  margin-bottom: 20px;
  background: #fff;
}

.right_main_bottom{
  height: 28%;
  margin-top: 20px;
  margin-left: 20px;
  background: #fff;
  box-sizing: border-box;
}
.bode_main{
  width: 100%;
  height: 100%;
  background: #fff;
  font-size: 20px;
  text-align: center;
  color:#000;
}

.total-layout {
  margin-top: 20px;
}


.total-frame {
  border: 1px solid #DCDFE6;
  padding: 20px;
  height: 100px;
}

.total-icon {
  color: #409EFF;
  width: 60px;
  height: 60px;
}

.total-title {
  position: relative;
  font-size: 16px;
  color: #909399;
  left: 70px;
  top: -50px;
}

.total-value {
  position: relative;
  font-size: 18px;
  color: #606266;
  left: 70px;
  top: -40px;
}

</style>
