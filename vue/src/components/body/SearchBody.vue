<template>
	<p>{{title}}</p>
	<el-table:data="searchResult">
		<el-table-column prop="name" label="小说名" width="180"></el-table-column>
	</el-table>
</template>

<script setup lang="ts">

import { ref } from 'vue'

defineProps<{
	title:string,
	inData:any
}>()


</script>

<style scoped>
	#book{
		margin: auto;
		width: 1100px;
		background-color: antiquewhite;
	}
	
</style>