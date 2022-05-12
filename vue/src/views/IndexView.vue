<template>
	<div id="header">
		<SortNav/>
	</div>
	
	<div id="body">
		<IndexBody @toBookView='toBookView' />
	</div>
</template>

<script setup lang="ts">
import SortNav from '@/components/nav/SortNav.vue'; 
import IndexBody from '@/components/body/IndexBody.vue'
const emits = defineEmits(['toBookView'])
const toBookView = (outData) => {
  emits('toBookView',outData)
}
</script>

<style scoped>
	#header{
		margin: auto;
		width: 1400px;
	}
	#body{
		margin: auto;
		width: 1400px;
	}
</style>