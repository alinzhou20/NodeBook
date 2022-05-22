<script setup lang="ts">
import { ref,reactive,toRaw,onMounted } from 'vue';
import axios from 'axios';

var Data = reactive({
      Detail: [],
    });

/* 查看书籍信息，暂未完成 */
onMounted(()=>{
	axios.get('/api/chapter/comment',{
		params:{
			bookId: window.sessionStorage.getItem('tar_book')
		}
	}).then(res=>{ 

		Data.Detail = JSON.parse(JSON.stringify(res.data.data));
	}).catch(err=>{console.log(err);})
}) 

</script>


<template>
	<h1>
		信息
	</h1>
</template>

