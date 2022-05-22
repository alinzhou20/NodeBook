<template>
	<div id="body">
		正文内容
	</div>
	<div id="content">
		{{Data.Detail}}
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
		Data.Detail = JSON.parse(JSON.stringify(res.data.data.content));
		open(res.data.msg)

	}).catch(err=>{console.log(err);})
	

}) 

const open = (msg) => {
  ElMessage(msg)
}


</script>

<style scoped>

	#body #content{
		
		text-align: left;
		padding: 10px;
		white-space: pre-wrap;
		margin: auto;
		margin-top: 40px;
		width: 1000px;
		background-color: aquamarine;
	}
</style>