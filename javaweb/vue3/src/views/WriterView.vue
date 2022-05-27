<script setup lang="ts">

import {ref,toRaw,onMounted,reactive,require} from 'vue'
import axios from 'axios';
import router from '@/router/index.ts';
onMounted(()=>{
		router.push({name:'writerNav'})
	}) 
</script>
<template>
	<router-view/>
</template>

