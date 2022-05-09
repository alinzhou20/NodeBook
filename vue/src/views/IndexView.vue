<template>
	<div id="header">
		<SortNav/>
	</div>
	
	<div id="body">
		<IndexBody/>
	</div>
</template>

<script setup lang="ts">
import SortNav from '@/components/nav/SortNav.vue'; 
import IndexBody from '@/components/body/IndexBody.vue'


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