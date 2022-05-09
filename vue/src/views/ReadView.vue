<template>
	<div id="body">
		<ReadBody :inRead="inRead"/>
	</div>
</template>

<script setup lang="ts">
import { ref,toRaw,reactive} from 'vue'
import ReadBody from '@/components/body/ReadBody.vue'; 

const props = defineProps<{
	inData: any
}>();
let inRead =  reactive({
	inDetail:[]
});
inRead.inDetail = toRaw(props.inData)
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