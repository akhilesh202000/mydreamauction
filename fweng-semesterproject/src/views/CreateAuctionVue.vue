<template>
    <div class="relative h-96 w-full flex items-center justify-center">
        <div class="absolute transform left-1/2 -translate-x-1/2 w-96 h-full">
            <div class="relative text-center space-y-6 flex flex-col items-center">
                <h2 class="text-6xl font-serif text-gray-700 z-50">
                    Create auction
                </h2>
                <h5>
                    These are the currently available auctions
                </h5>
                <table class="table-auto">
                    <thead>
                        <th>
                            <tr>
                                Title
                            </tr>
                        </th>
                        <th>
                            <tr>
                                Description
                            </tr>
                        </th>
                        <th>
                            <tr>
                                Starting date
                            </tr>
                        </th>
                        <th>
                            <tr>
                                start date
                            </tr>
                        </th>
                        <th>
                            <tr>
                                End date
                            </tr>
                        </th>
                        <th>
                            <tr>
                                category
                            </tr>
                        </th>
                    </thead>
                    <tbody>
                        <tr v-for="item in items" :key="item.message">
                            <td>
                                {{ item.name }}
                            </td>
                            <td>
                                {{ item.description }}
                            </td>
                            <td>
                                {{ item.startDate }}
                            </td>
                            <td>
                                {{ item.startDate }}
                            </td>
                            <td>
                                {{ item.endDate }}
                            </td>
                            <td>
                                {{ item.category }}
                            </td>
                        </tr>
                    </tbody>
                </table>

                <div class="text-4x1">
                    <label for="name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">name</label>
                    <input type="text" id="name" v-model="name" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="name" />

                    <label for="category"
                        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">category</label>
                    <input type="text" id="category" v-model="category" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="category" />

                    <label for="description"
                        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">description</label>
                    <input type="text" id="description" v-model="description" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="description" />


                    <label for="startDate" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">start
                        bidding</label>
                    <input type="date" id="startDate" v-model="startDate" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="startDate" />

                    <label for="endDate" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">close
                        bidding</label>
                    <input type="date" id="endDate" v-model="endDate" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="endDate" />

                    <button @click="create()"
                        class="bg-green-400 mr-5 mt-5 text-white px-4 py-2 rounded">create</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>

export default {
    name: 'CreateAuctionVue',
    data() {
        return {
            roleCheck: '',
            items: [
            ],
            name: '',
            category: '',
            description: '',
            startDate: '',
            endDate: ''
        }
    },
    methods: {
        getAuctions: async function () {
            await fetch("http://localhost:8080/auctions", {
                method: "GET",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                },
            }).then((response) => response.json())
                .then((data) => {
                    for (let i = 0; i < data.length; i++) {
                        if (localStorage.getItem("role") != "admin") {
                            if (data[i].locked != 1) {
                                this.items.push(data[i])
                            }
                        } else {
                            this.items.push(data[i])
                        }
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        },
        create: async function () {
            // var currDate = new Date()
            // var fullYear = (currDate.getFullYear()+"-"+(currDate.getMonth()+1)+"-"+currDate.getDate()).toString()
            // console.log(fullYear)
            await fetch("http://localhost:8080/auctions", {
                method: "POST",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                body: JSON.stringify(
                    {
                        name: this.name,
                        locked: "false",
                        category: this.category,
                        description: this.description,
                        startingPrice: "0",
                        currHighestPrice: "0",
                        startDate: this.startDate,
                        endDate: this.endDate,
                        username: localStorage.getItem("username")
                    }
                ),
            }).then((response) => response.json())
                .then((data) => {
                    console.log('sucess:', data)
                    alert("Created")
                })
                .catch((error) => {
                    console.error('Error:', error)
                });
            location.reload()
        },

    },
    beforeMount() {
        this.username = localStorage.getItem("username")
        this.roleCheck = localStorage.getItem("role")
        this.roleFunction = localStorage.getItem("rolefunction")
        if (this.roleCheck == 'admin' || this.roleFunction == 'buyer') {
            this.getAuctions()
        }
        else {
            this.$router.push({ path: '/' })
        }
    },
}
</script>
<style lang="">
</style>