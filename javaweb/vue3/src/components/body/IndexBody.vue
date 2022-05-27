<!-- 首页主体：排行榜，随机书籍 -->
<script setup lang="ts">
import {ref,toRaw,onMounted,reactive,require} from 'vue'
import axios from 'axios';

var Data = reactive({  //存储axios返回值
      Detail: [],
    });
const emits = defineEmits(['toBookView'])	
const tableData = [ //排行榜数据
  {
    date: '龙族',
  },
  {
    date: '斗罗',
  },
]
const getImg = function(url){
	let img = require('@/assets/ImgData/'+url);
	return img;
}

const toBookView = (bookId) => {
	/* 实践得出
	   常用的数据不能采用组件传输的方式
	   应该使用session进行统一存储*/
  window.sessionStorage.setItem('tar_book', bookId)
  emits('toBookView')
}

onMounted(()=>{
	axios.get('/api/book',{ //获得确定数量的随机书籍
		params:{
			number: 8,
			method: 'getCasualBook',
			
		}
	})
	.then(res=>{ 		
		Data.Detail = JSON.parse(JSON.stringify(res.data.data));		
	}).catch(err=>{console.log(err);})
	
	axios.get('/api/book',{ //获得确定数量的随机书籍
		params:{
			method: 'getTopBook',
			tag:'0'
		}
	})
	.then(res=>{ 		
		Data.Detail = JSON.parse(JSON.stringify(res.data.data));		
	}).catch(err=>{console.log(err);})
}) 
</script>

<template>
	<div id="index">

<!-- 第一主题 -->
	<el-row>
		<!-- 排行榜 -->
		<el-col :span="2" :offset="1">
			<el-table :data="tableData" size="large">
				<el-table-column prop="date" label="热门排行"/>
			</el-table>
		</el-col>
	
		<!-- 随机出现的书籍 -->
		<el-col :span="19" :offset="1">
			<el-row :gutter="20" style="margin-top: 20px;">
				<el-col :span="6" v-for="item in Data.Detail" :key="item" style="margin-top: 40px;">
					<div class="item">
						<el-card :body-style="{ padding: '0px'}" style='background-color: #fff9ea; border: 0;'> <!-- #ffffff6e可以调整不透明度 -->
						<img style="width: 160px; height: 240px; " :src="getImg(item.imgUrl)" class="image"/>
						<div>
							<span>{{item.name}}</span>
							<div class="bottom">
								<el-button type="text" class="button" @click="toBookView(item.id)" 
								style='color: #ff5582; border: 0;'>点击查看</el-button>
							</div>
						</div>
					  </el-card>
					</div>
				</el-col>
			</el-row>
		</el-col>
	
	</el-row>			
		
		
	</div>
</template>

<style scoped>
	#index{
		margin: auto;
		margin: 0;
		margin-top: 20px;
		width: 1400px;
	}	
	#index div{
		margin: auto;
	}
	.image{
		width: 200px;
	}
	#item{
		padding-top: 20px;
	}

</style>