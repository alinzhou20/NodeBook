<!-- 首页视图 -->
<script setup lang="ts">
import SortNav from '@/components/nav/SortNav.vue'; 
import IndexBody from '@/components/body/IndexBody.vue'


const emits = defineEmits(['toBookView'])
/* 给IndexBody子组件提供调用的方法 */
const toBookView = (outData) => {
	
  /* 调用Home父组件的toBookView方法 */
  emits('toBookView',outData)
}
</script>

<template>
	<div id="sort">
		<SortNav/>
	</div>
	<div id="body">
		<IndexBody @toBookView='toBookView' />
	</div>
</template>

<style scoped>
	#sort{
		margin: auto;
		width: 1400px;
	}
	#body{
		margin: auto;
		width: 1400px;
	}
</style>