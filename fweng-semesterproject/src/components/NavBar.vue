<template>
   <section class="relative mx-auto">
      <nav class="flex justify-between bg-gray-900 text-white w-screen">
         <div class="px-5 xl:px-12 py-6 flex w-full items-center">
            <a class="text-3xl font-bold font-heading" href="/home">
               MyDreamAuction
            </a>
            <ul class="hidden md:flex px-4 mx-auto font-semibold font-heading space-x-12">
               <li v-if="role == null">
                  <NavBarItem to="/" text="Login" />
               </li>
               <li v-if="role == null">
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
               <li v-if="role == 'admin' || role == 'user'">
                  <NavBarItem to="/profile" text="Profile" />
               </li>
               <li v-if="role == 'admin'">
                  <NavBarItem to="/admin" text="Users" />
               </li>
               <li v-if="role == 'admin'">
                  <NavBarItem to="/product" text="Products" />
               </li>
               <li v-if="role == 'admin' || roleFunction == 'bidder'">
                  <NavBarItem to="/auctiondetail" text="Auction detail" />
               </li>
               <li v-if="roleFunction == 'buyer'">
                  <NavBarItem to="/createauction" text="Create auction" />
               </li>
               <li v-if="roleFunction == 'bidder'">
                  <NavBarItem to="/mybids" text="My Bids" />
               </li>
               <li v-if="roleFunction == 'buyer'">
                  <NavBarItem to="/myoffers" text="My Offers" />
               </li>

               <li v-if="role == 'admin' || role == 'user'">
                  <button id="logout" @click="logout"
                     class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded-full">LogOut</button>
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
   data() {
      return {
         role: '',
         roleFunction: ''
      }
   },
   methods: {
      logout() {
         localStorage.clear()
         window.location.reload()
         this.$router.push({ path: '/home' })
      }
   },
   beforeMount() {
      this.role = localStorage.getItem('role')
      this.roleFunction = localStorage.getItem('rolefunction')
   },
   components: {
      NavBarItem,
   }
}
</script>
<style>

</style>