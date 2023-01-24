<template lang="">
   <router-link :to="to" class="font-semibold p-2 hover:scale-125 text-white">{{text}}</router-link>
</template>
<script>
export default {
   name: 'NavBarItem',
   props: {
      to: {type: String, required: true},
      text: {type: String, required: true},
   }
}
</script>
<style lang="">
   
</style>