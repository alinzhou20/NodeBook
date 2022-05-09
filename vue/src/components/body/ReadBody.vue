<template>

	<div id="body">
		正文内容
	</div>
	<h1>{{msg}}</h1>
	<div id="content">
		{{chapterData.chapterDetail}}
	</div>
</template>

<script setup lang="ts">
import { ref,reactive,toRaw,onMounted } from 'vue';
import axios from 'axios';
const props = defineProps<{
	inRead: any
}>();

var chapterData = reactive({
      chapterDetail: '',
    });

console.log(chapterData.chapterDetail)
onMounted(()=>{
	
	axios.get('/api/book/getContent',{
		params:{
			chapterId: toRaw(props.inRead.inDetail.inDetail.chapterId)
		}
	}).then(res=>{ 
		chapterData.chapterDetail = JSON.parse(JSON.stringify(res.data.data.content));

	}).catch(err=>{console.log(err);})
	

}) 


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