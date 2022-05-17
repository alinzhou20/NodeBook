<script setup lang="ts">
/* 多表单 */	
import { ref,reactive,toRaw,onMounted } from 'vue'
import router from '@/router/index.ts'
import axios from 'axios';

let BookNav =  reactive({
	Detail:[]
});

onMounted(()=>{
	axios.get('/api/book/getById',{
		params:{
			bookId: window.sessionStorage.getItem('tar_book')
		}
	})
	.then(res=>{ 		
		BookNav.Detail = JSON.parse(JSON.stringify(res.data.data));
	}).catch(err=>{console.log(err);})
	
}) 

/* 跳转到阅读界面 */
function toChapter(){	
	router.push({name:'chapter'})
}
</script>


<template>
	<div id="book">
		<el-row :gutter="10">			  
		    <el-col :span="6" >
				<el-image style="width: 160px; height: 200px; " :src="logo_src" />
			</el-col>
			<el-col :span="16" :offset="2">
				<el-row>
					<el-col :span="6">
						<!-- 书名 -->
						{{BookNav.Detail.name}}
					</el-col>
					<el-col :span="6">
						<!-- 作者 -->
						{{BookNav.Detail.writer}}
					</el-col>
				</el-row>
				
				<el-row>
					<el-col :span="6">
						<!-- 操作 -->
						放入书架
					</el-col>
					<el-col :span="6">
					  <el-button type="primary" @click="toChapter">开始阅读</el-button>
					</el-col>
				</el-row>
		    </el-col>
		</el-row>
	</div>
</template>


<style scoped>
	#book{
		margin: auto;
		width: 1100px;
		background-color: antiquewhite;
	}
	
</style>