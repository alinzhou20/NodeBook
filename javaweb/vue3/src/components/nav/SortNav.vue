<template>
	
<div id="nav">
	
	<!-- 导航分类栏 -->
  <el-row style="background-color: #909399;">
	  
    <el-col :span="4" :offset="3" >
		<div class="item">
			作品分类
		</div>
	</el-col>

	<el-col :span="4" :offset="2">
		<div class="item">
			排行榜
		</div>
	</el-col>

	<el-col :span="4">
		<div class="item">
			作家专区
		</div>
	</el-col>

	<el-col :span="4">
		<div class="item">
			充值中心
		</div>
	</el-col>
	
	
	
  </el-row>
  <!-- 简介展示栏 -->
  <el-row>
  	  
    <el-col :span="6" :offset="2" >
		
  		<table id="sort">
			<tr>
				<td  @click="classify('都市')">都市</td><td >玄幻</td><td >历史</td>
			</tr>
			<tr>
				<td >同人</td><td  @click="classify('武侠')">武侠</td><td>异界</td>
			</tr>
			<tr>
				<td>出版</td><td>完结经典</td><td>火热连载</td>
			</tr>
			
		</table>
		
  	</el-col>

  	<el-col :span="12" :offset="2">
		<div id="light">
			<el-carousel :interval="4000" type="card" height="200px">
			    <el-carousel-item v-for="item in Data.Detail" :key="item">
			      <el-image style="width: 350px; height: 200px; " :src="getImg(item.imgUrl)" />
			    </el-carousel-item>
			  </el-carousel>
		</div>  	
  	</el-col>
   	
  </el-row>
  

</div>
</template>

<script setup lang="ts">
import {ref,toRaw,onMounted,reactive,require} from 'vue'
import axios from 'axios';
const emits = defineEmits(['toSearchView'])
const getImg = function(url){
	let img = require('@/assets/ImgData/'+url);
	return img;
}
/* 走马灯 */
var Data = reactive({  //存储返回值
      Detail: [],
    });
onMounted(()=>{
	axios.get('/api/book',{
		params:{
			number: 3,
			method: 'getCasualBook'
		}
	})
	.then(res=>{ 		
		Data.Detail = JSON.parse(JSON.stringify(res.data.data));	
	}).catch(err=>{console.log(err);})
}) 

const classify = (val) =>{
	/* 根据标签搜索书籍 */
	axios.get('/api/book',{
		params:{
			tag: val,
			method: 'getBookByTag'
		}
	})
	.then(res=>{ 		
		Data.Detail = JSON.parse(JSON.stringify(res.data.data));
		emits('toSearchView',Data.Detail)		
	}).catch(err=>{console.log(err);})
	
	
}
</script>



<style scoped>
	#nav{
		height: 300px;
	}
	
	
	.item{
		font-size:25px;
		color: azure;
	}
	
	/* 分类表格 */
	#sort{
		background-color: #909399 ;
		font-size: 20px;
		color: beige;
	}
	#sort td{
		width: 200px;
		height: 80px;
	}
	
	/* 走马灯 */
	
	#light{
		margin-top: 25px;
	}
	

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

</style>

