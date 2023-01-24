<template>
    <div class="relative h-96 w-full flex items-center justify-center">
        <div class="absolute transform left-1/2 -translate-x-1/2 w-96 h-full">
            <div class="relative text-center space-y-6 flex flex-col items-center">
                <h2 class="text-6xl font-serif text-gray-700 z-50">
                    Edit products
                </h2>
                <table class="min-w-full divide-y divide-gray-200">
                    <thead class="bg-gray-50">
                        <th>
                            <tr>
                                id
                            </tr>
                        </th>
                        <th>
                            <tr>
                                name
                            </tr>
                        </th>
                        <th>
                            <tr>
                                description
                            </tr>
                        </th>
                    </thead>
                    <tbody class="divide-y divide-gray-200">
                        <tr v-for="item in items" :key="item.id">
                            <td class="tmp">
                                {{ item.id }}
                            </td>
                            <td class="tmp">
                                {{ item.name }}
                            </td>
                            <td class="tmp">
                                {{ item.description }}
                            </td>
                            <td>
                                <button
                                    class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full"
                                    @click="editproduct(item.id)">
                                    edit
                                </button>
                            </td>
                            <td>
                                <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded-full"
                                    @click="deleteProduct(item.id)">
                                    delete
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <h5 class="text-6xl font-serif text-gray-700 z-50">
                    Add
                </h5>
                <div class="text-4x1">
                    <label for="name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">name</label>
                    <input type="text" id="name" v-model="name" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="name" />

                    <label for="description"
                        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">description</label>
                    <input type="text" id="description" v-model="description" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="description" />

                    <label for="auction"
                        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">auction</label>
                    <select id="auction" v-model="auction" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500">
                        <option v-for="item in auctions" :key="item.id">{{ item.name + ":" + item.id }}</option>
                    </select>
                    <button @click="create(auction)"
                        class="bg-green-400 mr-5 mt-5 text-white px-4 py-2 rounded">create</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>

export default {
    name: 'ProductVue',
    data() {
        return {
            roleCheck: '',
            name: '',
            items: [
            ],
            auctions: [

            ],
            auction: '',
            description: ''
        }
    },
    methods: {
        getProduct: async function () {
            await fetch("http://localhost:8080/products", {
                method: "GET",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }).then((response) => response.json())
                .then((data) => {
                    for (let i = 0; i < data.length; i++) {
                        this.items.push(data[i])
                        console.log(data[i])
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        },
        deleteProduct: async function (id) {
            await fetch("http://localhost:8080/products/" + id, {
                method: "DELETE",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
            }).then(location.reload())
        },
        editproduct(id) {
            location.href = "editproduct?product=" + id
        },
        create: async function (auction) {
            let id = auction.split(":")[1]
            console.log(auction)
            await fetch("http://localhost:8080/auctions/" + id, {
                method: "PATCH",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                body: JSON.stringify(
                    {
                        products: [
                            {
                                name: this.name,
                                description: this.description
                            }
                        ]
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
            location.reload()
        },
        getAuctions: async function () {
            await fetch("http://localhost:8080/auctions", {
                method: "GET",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }).then((response) => response.json())
                .then((data) => {
                    for (let i = 0; i < data.length; i++) {
                        this.auctions.push(data[i])
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        },
    },
    beforeMount() {
        this.roleCheck = localStorage.getItem("role")
        if (this.roleCheck == 'admin') {
            this.getAuctions()
            this.getProduct()
        } else {
            this.$router.push({ path: '/' })
        }
    },
}
</script>
<style lang="">
</style>