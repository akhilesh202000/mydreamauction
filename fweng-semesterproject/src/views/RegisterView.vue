<template>
   <div class="relative mt-14 h-96 w-full flex items-center justify-center">
      <div class="relative text-center space-y-6 flex flex-col items-center">
         <h2 class="text-6xl font-serif text-gray-700 z-50">
            Register
         </h2>
         <div class="text-4x1">
            <label for="username" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">username</label>
            <input type="text" id="username" v-model="username" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="username" />

            <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">email</label>
            <input type="text" id="email" v-model="email" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="email" />


            <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">password</label>
            <input type="password" id="password" v-model="password" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="password" />

            <label for="roleFunction"
               class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">roleFunction</label>
            <select id="roleFunction" v-model="roleFunction" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500">
               <option>bidder</option>
               <option>buyer</option>
            </select>

            <button @click="register" class="bg-pink-400 text-white px-4 py-2 rounded">
               Register</button>
         </div>
      </div>
   </div>
</template>
<script>
// import axios from 'axios'
export default {
   name: 'RegisterView',
   data() {
      return {
         username: '',
         password: '',
         email: '',
         roleFunction: ''
      }
   },
   methods: {
      async register() {
         await fetch("http://localhost:8080/register", {
            method: "POST",
            headers: {
               "Accept": "application/json",
               "Content-Type": "application/json",
            },
            body: JSON.stringify({
               username: this.username,
               password: this.password,
               locked: 0,
               role: 'user',
               email: this.email,
               roleFunction: this.roleFunction

            }),
         }).then((response) => response.json())
            .then((data) => {
               console.log('Success:', data);
               confirm('Registred to MyDreamAuction')
               this.$router.push({ path: '/' })
            })
            .catch((error) => {
               console.error('Error:', error);
            });
      }
   }
}
</script>
<style>

</style>