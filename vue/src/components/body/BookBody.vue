<template>
	<div id="header">
	</div>
	<div id="body">
		正文内容
	</div>
	<el-table :data="toRaw(Data.Detail)" style="width: 100%">
	   <el-table-column prop="list" label="章节号" width="180" />
	   <el-table-column prop="title" label="章节名" width="180" />
	   <el-table-column align="right">
	         <template #default="scope">
	           <el-button
	             size="small"
	             type="danger"
	             @click="toRead(scope.$index, scope.row)"
	             >点击查看</el-button
	           >
	         </template>
	       </el-table-column>
	 </el-table>
</template>

<script setup lang="ts">
import { ref,reactive,toRaw,onMounted } from 'vue';
import axios from 'axios';
const props = defineProps<{
	inBook: any
}>();

var Data = reactive({
      Detail: [],
    });
	
	
/* 从后端获取书籍目录 */
onMounted(()=>{
	axios.get('/api/chapter/searchByBookId',{
		params:{
			bookId: toRaw(props.inBook.inDetail.inDetail.id)
		}
	}).then(res=>{ 
		/* 此处可以获取外部的Data */
			console.log(res)
		Data.Detail = JSON.parse(JSON.stringify(res.data.data));
	}).catch(err=>{console.log(err);})
	
	/* 父子组件传递的时候必须要求是ref 或者 reactive*/
	
}) 

const emits =  defineEmits(['toRead'])
interface Capter {
  id: number
}
const toRead = (index: number, row: Capter) => {
	const outData = toRaw(row)
	emits('toRead',outData)
}

</script>

<style scoped>
	#header{
		margin: auto;
		width: 1400px;
	}
	#body{
		
		margin: auto;
		margin-top: 40px;
		width: 1000px;
		background-color: aquamarine;
	}
</style>