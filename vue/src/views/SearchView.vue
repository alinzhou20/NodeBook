<template>
	<p>{{title}}</p>

 <el-table :data="toRaw(props).inData.inDetail.searchDetail.data" style="width: 100%">
    
    <el-table-column prop="name" label="Name" width="180" />
    <el-table-column align="right">
          <template #default="scope">
            <el-button
              size="small"
              type="danger"
              @click="toBook(scope.$index, scope.row)"
              >点击查看</el-button
            >
          </template>
        </el-table-column>
  </el-table>
</template>

<script setup lang="ts">

import { ref,toRaw} from 'vue'

/* 接收父组件给的参数 */
const props = defineProps<{
	inData: any
}>();

/* 跳转书籍 */
interface Book {
  name: string
}

const emits = defineEmits(['outSearch'])
const toBook = (index: number, row: Book) => {
  const outData = toRaw(row)
  emits('outSearch',outData)
}


</script>

<style scoped>
	#book{
		margin: auto;
		width: 1100px;
		background-color: antiquewhite;
	}
	
</style>