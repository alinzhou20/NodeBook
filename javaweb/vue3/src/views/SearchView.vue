<script setup lang="ts">

import {ref,toRaw} from 'vue'

/* 接收父组件给的参数 */
const props = defineProps<{
	inData: any
}>();

/* 跳转书籍 */
interface Book {
  bookId:number
  name: string
}

const emits = defineEmits(['toBookView'])
const toBookView = (index: number, row: Book) => {
  const outData = toRaw(row).id
  window.sessionStorage.setItem('tar_book', outData)
  emits('toBookView',outData)
}

console.log(props.inData)


</script>


<template>

 <el-table :data="props.inData.inDetail" style="width: 100%">
    
    <el-table-column prop="name" label="Name" width="180" />
    <el-table-column align="right">
          <template #default="scope">
            <el-button
              size="small"
              type="danger"
              @click="toBookView(scope.$index, scope.row)"
              >点击查看</el-button
            >
          </template>
        </el-table-column>
  </el-table>
</template>


<style scoped>
	#book{
		margin: auto;
		width: 1100px;
		background-color: antiquewhite;
	}
	
</style>