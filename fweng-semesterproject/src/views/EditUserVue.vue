<template>
    <div class="relative h-96 w-full flex items-center justify-center">
        <div class="absolute transform left-1/2 -translate-x-1/2 w-96 h-full">
            <div class="relative text-center space-y-6 flex flex-col items-center">
                <h2 class="text-6xl font-serif text-gray-700 z-50">
                    Admin
                </h2>
                <div class="text-4x1">
                    <label for="username"
                        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">username</label>
                    <input type="text" id="username" v-model="username" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="username" />

                    <label for="email"
                        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">email</label>
                    <input type="text" id="email" v-model="email" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="email" />

                    <label for="role" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">role</label>
                    <input type="text" id="role" v-model="role" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="role" />

                    <label for="status"
                        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">status</label>
                    <input type="text" id="status" v-model="status" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="status" />

                    <button @click="edit(username, role, status, email)"
                        class="bg-pink-400 text-white px-4 py-2 rounded">
                        edit</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>

export default {
    name: 'EditUserVue',
    data() {
        return {
            username: '',
            role: '',
            status: '',
            email: ''
        }
    },
    methods: {
        getUser: async function () {
            await fetch("http://localhost:8080/users/" + this.usernameapi, {
                method: "GET",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }).then((response) => response.json())
                .then((data) => {
                    this.username = data.username;
                    this.status = data.status;
                    this.role = data.role;
                    this.email = data.email;
                    console.log(data)
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        },
        edit: async function (username, role, status, email) {
            await fetch("http://localhost:8080/users/admin/" + this.usernameapi, {
                method: "PATCH",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                body: JSON.stringify(
                    {
                        username: username,
                        role: role,
                        status: status,
                        email: email
                    }
                ),
            }).then((response) => response.json())
                .then((data) => {
                    console.log('sucess:', data)
                    alert("Updated")
                })
                .catch((error) => {
                    console.error('Error:', error)
                });
        },
    },
    beforeMount() {
        this.usernameapi = window.location.href.split('?')[1].split('=')[1]
        this.getUser()
    },
}
</script>
<style lang="">
</style>