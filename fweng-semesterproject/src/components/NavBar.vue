<template>
   <section class="relative mx-auto">
      <nav class="flex justify-between bg-gray-900 text-white w-screen">
         <div class="px-5 xl:px-12 py-6 flex w-full items-center">
            <a class="text-3xl font-bold font-heading" href="/">
               MyDreamAuction
            </a>
            <ul class="hidden md:flex px-4 mx-auto font-semibold font-heading space-x-12">
               <li v-if="role===null">
                  <NavBarItem to="/" text="Login" />
               </li>
               <li v-if="role===null">
                  <NavBarItem to="/register" text="Regsiter" />
               </li>
               <li>
                  <NavBarItem to="/home" text="Home" />
               </li>
               <li>
                  <NavBarItem to="/help" text="Help" />
               </li>
               <li>
                  <NavBarItem to="/imprint" text="Imprint" />
               </li>
               <li v-if="role!==null">
                  <NavBarItem to="/profile" text="Profile" />
               </li>
               <li v-if="role==='admin'">
                  <NavBarItem to="/admin" text="Admin" />
               </li>
            </ul>
         </div>
         <a class="navbar-burger self-center mr-12 xl:hidden" href="#">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 hover:text-gray-200" fill="none" viewBox="0 0 24 24"
               stroke="currentColor">
               <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
            </svg>
         </a>
      </nav>
   </section>
   <router-view />
</template>
<script>
import NavBarItem from '@/components/atoms/NavBarItem.vue';

export default {
   name: 'NavBar',
   data(){
      return{
         role: localStorage.getItem('role'),
      }
   },
   components: {
      NavBarItem,
   }
}
</script>