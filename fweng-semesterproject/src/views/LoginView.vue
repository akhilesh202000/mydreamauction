<template>
   <div class="relative mt-0 h-96 w-full flex items-center justify-center">
      <div class="relative text-center space-y-6 flex flex-col items-center">
         <h2 class="text-6xl font-serif text-gray-700 z-50">
            Login
         </h2>
         <div class="text-4x1">
            <label for="username" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">username</label>
            <input type="text" id="username" v-model="username" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="username" />

            <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">password</label>
            <input type="password" id="password" v-model="password" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="password" />

            <button @click="authenticate" class="bg-pink-400 text-white px-4 py-2 rounded"> Login
            </button>
         </div>
      </div>
   </div>
</template>
<script>
export default {
   name: 'LoginView',
   data() {
      return {
         token: '',
         username: '',
         password: '',
      }
   },
   methods: {
      async authenticate() {
         await fetch("http://localhost:8080/authenticate", {
            method: "POST",
            headers: {
               "Accept": "application/json",
               "Content-Type": "application/json",
            },
            body: JSON.stringify({
               username: this.username,
               password: this.password,
            }),
         }).then((response) => response.json())
            .then(async (data) => {
               if (data.status == 401) {
                  alert('This user does not exist');
               } else {
                  console.log('Success:', data.status);
                  this.token = data.token
                  await this.getRole(this.token)
                  localStorage.setItem("token", data.token)
               }
            })
            .catch((error) => {
               alert('This user does not exist');
               console.error('Error:', error);
            });
      },
      async getRole(token) {
         await fetch("http://localhost:8080/users/" + this.username, {
            method: "GET",
            headers: {
               "Accept": "application/json",
               "Content-Type": "application/json",
               "Authorization": "Bearer " + token
            }
         }).then((response) => response.json())
            .then((data) => {
               console.log(data.locked)
               if (data.locked == 0) {
                  console.log('Success:', data)
                  localStorage.setItem("role", data.role)
                  localStorage.setItem("username", data.username)
                  localStorage.setItem("locked", data.locked)
                  localStorage.setItem("rolefunction", data.roleFunction)

                  location.reload()
                  this.$router.push({ path: '/home' })
               } else {
                  alert("User is locked")
                  return
               }
            })
            .catch((error) => {
               console.error('Error:', error)
            });
      }
   }
}
</script>