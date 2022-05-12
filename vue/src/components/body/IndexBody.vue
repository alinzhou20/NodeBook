<template>
	<div id="index">

	<el-row>
		
		<el-col :span="2" :offset="1">
			<el-table :data="tableData" size="large">
				<el-table-column prop="date" label="玄幻排行"/>
			</el-table>
		</el-col>
	
		
	
		<el-col :span="19" :offset="1">
			<el-row :gutter="20" style="margin-top: 20px;">
				<el-col :span="6" v-for="item in Data.Detail" :key="item" style="margin-top: 40px;">
					<div class="item">
						<el-card :body-style="{ padding: '0px'}" style='background-color: #fff9ea; border: 0;'> <!-- #ffffff6e可以调整不透明度 -->
						<img style="width: 350px; height: 200px; " :src="getImg(item.imgUrl)" class="image"/>
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
<script setup lang="ts">
/* 列表 */
import {ref,toRaw,onMounted,reactive,require} from 'vue'
import axios from 'axios';
const getImg = function(url){
	let img = require('@/assets/ImgData/'+url);
	return img;
}
var Data = reactive({  //存储返回值
      Detail: [],
    });
onMounted(()=>{
	axios.get('/api/book/getCasualBook',{
		params:{
			number: 8
		}
	})
	.then(res=>{ 		
		Data.Detail = JSON.parse(JSON.stringify(res.data.data));
		console.log(Data.Detail)		
	}).catch(err=>{console.log(err);})
}) 


const tableData = [
  {
    date: '龙族',
  },

]



const emits = defineEmits(['toBookView'])
const toBookView = (outData) => {
  console.log(outData)
  window.sessionStorage.setItem('tar_book', outData)
  emits('toBookView',outData)
}
</script>



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