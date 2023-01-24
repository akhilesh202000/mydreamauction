<template>
    <div v-if="roleCheck == 'admin'" class="relative h-96 w-full flex items-center justify-center">
        <div class="absolute transform left-1/2 -translate-x-1/2 w-96 h-full">
            <div class="relative text-center space-y-6 flex flex-col items-center">
                <h2 class="text-6xl font-serif text-gray-700 z-50">
                    Admin
                </h2>
                <div class="text-4x1">
                    <label for="productname"
                        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">productname</label>
                    <input type="text" id="productname" v-model="productname" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="productname" />

                    <label for="description"
                        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">description</label>
                    <input type="text" id="description" v-model="description" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="description" />

                    <button @click="edit(id)" class="bg-pink-400 text-white px-4 py-2 rounded">
                        edit</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>

export default {
    name: 'EditProductVue',
    data() {
        return {
            roleCheck: '',
            id: '',
            productname: '',
            description: ''
        }
    },
    methods: {
        getProduct: async function () {
            await fetch("http://localhost:8080/products/" + this.id, {
                method: "GET",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }).then((response) => response.json())
                .then((data) => {
                    this.productname = data.productname;
                    console.log(data)
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        },
        edit: async function () {
            if (this.description != '' && this.productname != '') {

                await fetch("http://localhost:8080/products/" + this.id, {
                    method: "PATCH",
                    headers: {
                        "Accept": "application/json",
                        "Content-Type": "application/json",
                        "Authorization": "Bearer " + localStorage.getItem("token")
                    },
                    body: JSON.stringify(
                        {
                            name: this.productname,
                            description: this.description
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
            }
        },
    },
    beforeMount() {
        this.roleCheck = localStorage.getItem("role")
        if (this.roleCheck == "admin") {
            this.id = window.location.href.split('?')[1].split('=')[1]
            this.getProduct()
        } else {
            this.$router.push({ path: '/' })
        }
    },
}
</script>
<style lang="">
</style>