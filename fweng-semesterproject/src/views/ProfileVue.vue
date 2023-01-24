<template>
    <div v-if="roleCheck == 'admin' || roleCheck == 'user'"
        class="relative mt-8 h-96 w-full flex items-center justify-center">
        <div class="relative text-center space-y-6 flex flex-col items-center">
            <h2 class="text-6xl font-serif text-gray-700 z-50">
                Profile
            </h2>
            <div class="text-4x1">
                <label for="username"
                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">username</label>
                <input type="text" id="username" v-model="username" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="username">

                <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">email</label>
                <input type="text" id="email" v-model="email" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="email" />

                <label for="password"
                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">password</label>
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
                    <option disabled value="">Please select one</option>
                    <option>buyer</option>
                    <option>bidder</option>
                </select>
                <button @click="update" class="bg-pink-400 mr-5 mt-5 text-white px-4 py-2 rounded">Update</button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: 'ProfilVue',
    data() {
        return {
            usernameapi: '',
            roleCheck: '',
            tmpUsername: localStorage.getItem("username"),
            username: '',
            password: '',
            roleFunction: '',
            email: ''
        }
    },
    methods: {
        async update() {
            if (this.password == '') {
                alert("Geben sie ihr passwort oder ein neues ein")
            }
            await fetch("http://localhost:8080/users/" + this.tmpUsername, {
                method: "PATCH",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                body: JSON.stringify({
                    username: this.username,
                    password: this.password,
                    role:'user',
                    roleFunction: this.roleFunction,
                    locked: 0,
                    email: this.email
                }),
            }).then((response) => response.json())
                .then((data) => {
                    console.log('Success:', data);
                    confirm('Updated profile. LogIn again')
                    this.logout()
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        },
        getUser: async function (usernameapi) {
            await fetch("http://localhost:8080/users/" + usernameapi, {
                method: "GET",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }).then((response) => response.json())
                .then((data) => {
                    console.log(data)
                    this.username = data.username;
                    this.email = data.email;
                    console.log(data)
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        },
        logout() {
         localStorage.clear()
         window.location.reload()
         this.$router.push({ path: '/home' })
      }
    }, beforeMount() {
        this.roleCheck = localStorage.getItem("role")
        if (this.roleCheck == 'admin' || this.roleCheck == 'user') {
            this.usernameapi = localStorage.getItem('username')
            this.getUser(this.usernameapi)
        }
        else {
            this.$router.push({ path: '/' })
        }
    }
}
</script>
<style>

</style>