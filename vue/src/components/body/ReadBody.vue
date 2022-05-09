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
const props = defineProps<{
	inRead: any
}>();

var Data = reactive({
      Detail: '',
    });

onMounted(()=>{
	
	axios.get('/api/chapter/searchById',{
		params:{
			chapterId: toRaw(props.inRead.inDetail.inDetail.id)
		}
	}).then(res=>{ 
		console.log(res)
		Data.Detail = JSON.parse(JSON.stringify(res.data.data.content));

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