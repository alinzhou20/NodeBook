<template>
	<div id="read">
		<div id="title">
			{{Data.Detail.title}}
		</div>
		
		<div id="content">
			{{Data.Detail.content}}
		</div>
	
	</div>
	
</template>

<script setup lang="ts">
import { ref,reactive,toRaw,onMounted } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus'

const props = defineProps<{
	inRead: any
}>();

var Data = reactive({
      Detail: '',
    });

onMounted(()=>{
	axios.get('/api/user',{
		params:{
			userId: window.sessionStorage.getItem('user'),
			chapterId: window.sessionStorage.getItem('tar_chapter'),
			method: 'buyChapter'
		}
	}).then(res=>{ 
		Data.Detail = JSON.parse(JSON.stringify(res.data.data));
		console.log(Data.Detail)
		open(res.data.msg)

	}).catch(err=>{console.log(err);})
	
}) 

const open = (msg) => {
  ElMessage(msg)
}


</script>

<style scoped>

	#read{
		
		text-align: left;
		padding: 10px;
		white-space: pre-wrap;
		margin: auto;
		margin-top: 40px;
		width: 1000px;
		font-size: 18px;
		background-color: #f7f3e9;
	}
	#title {
		text-align: center;
		white-space: pre-wrap;
		margin: auto;
		margin-bottom: 40px;
		width: 1000px;
		font-size: 30px;
		
	}
</style>