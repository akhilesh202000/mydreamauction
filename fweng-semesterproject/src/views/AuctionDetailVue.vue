<template>
    <div class="relative h-96 w-full flex items-center justify-center">
        <div class="absolute transform left-1/2 -translate-x-1/2 w-96 h-full">
            <div class="relative text-center space-y-6 flex flex-col items-center">
                <h2 class="text-6xl font-serif text-gray-700 z-50">
                    Auction Detail
                </h2>
                <table class="min-w-full divide-y divide-gray-200">
                    <thead class="bg-gray-50">
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
                                category
                            </tr>
                        </th>
                        <th>
                            <tr>
                                start Date
                            </tr>
                        </th>
                    </thead>
                    <tbody class="divide-y divide-gray-200">
                        <tr v-for="item in items" :key="item.message">
                            <td>
                                {{ item.name }}
                            </td>
                            <td>
                                {{ item.description }}
                            </td>
                            <td>
                                {{ item.category }}
                            </td>
                            <td>
                                {{ item.startDate }}
                            </td>
                            <td v-if="roleCheck == 'admin'">
                                <button :id="item.id" @click="blockAuction(item.id, item.locked)"
                                    class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded-full">
                                    {{
                                        item.locked == 1 ? "open" : "lock"
                                    }}
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <h5 class="text-6xl font-serif text-gray-700 z-50">
                    Bid
                </h5>

                <table class="min-w-full divide-y divide-gray-200">
                    <thead class="bg-gray-50">
                        <tr>
                            <th>
                                Auction|
                            </th>
                            <th>
                                Products|
                            </th>
                        </tr>
                    </thead>
                    <tbody class="divide-y divide-gray-200">
                        <tr v-for="item in items" :key="item.id">
                            <td>
                                {{ item.name }}

                            </td>
                            <td v-for="product in item.products" :key="product.id">
                        <tr>
                            <td>
                        <tr> {{ product.name + " " + product.description }}</tr>
                        <tr>
                            <span>
                                <input type="text" :id="'price ' + product.id" placeholder="price" />
                                <input type="text" :id="'quantity ' + product.id" placeholder="quantity" />
                                <input type="date" :id="'deliverydate ' + product.id" placeholder="deliverydate" />
                            </span>
                        </tr>
                        <tr>

                            <button :id="'button ' + product.id" @click="bid(product.id, item.id)"
                                class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded-full">
                                Bid
                            </button>
                        </tr>

                        </td>

                        </tr>
                        </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>
<script>
import { toRaw } from 'vue'
export default {
    name: 'AuctionDetailVue',
    data() {
        return {
            roleCheck: '',
            items: [
            ],
            products: [
            ],
            isblocked: '',
            username: '',
            usernameFk: '',
            role: '',
            locked: '',
            email: '',
            roleFunction: ''
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
                    this.getProducts()
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        },
        getProducts: async function () {
            await fetch("http://localhost:8080/products", {
                method: "GET",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },

            }).then((response) => response.json())
                .then((data) => {
                    for (let i = 0; i < data.length; i++) {
                        this.products.push(data[i])
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
            console.log(toRaw(this.products))
        },
        getUser: async function () {
            await fetch("http://localhost:8080/users/" + this.username, {
                method: "GET",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }).then((response) => response.json())
                .then((data) => {
                    this.usernameFk = data.username;
                    this.locked = data.locked;
                    this.role = data.role;
                    this.email = data.email;
                    console.log(data)
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        },
        bid: async function (id) {
            let price = document.getElementById("price " + id).value
            let quantity = document.getElementById("quantity " + id).value
            let deliverydate = document.getElementById("deliverydate " + id).value

            console.log(id)
            await fetch("http://localhost:8080/products/bids/" + id, {
                method: "PATCH",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                body: JSON.stringify(
                    {
                        bids: [
                            {
                                name: localStorage.getItem("username"),
                                price: price,
                                quantity: quantity,
                                deliverydate: deliverydate,
                            }
                        ]
                    }
                ),
            }).then((response) => response.json())
                .then(async (data) => {
                    console.log('sucess:', data)
                    alert("Successfully bidded")
                })
                .catch((error) => {
                    console.error('Error:', error)
                });
        },
        getAuction: async function (id) {
            var x = null
            await fetch("http://localhost:8080/auctions/" + id, {
                method: "GET",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
            }).then((response) => response.json())
                .then((data) => {
                    x = data
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
            return x
        },
        getHighest: async function (id) {
            var x = 0
            await fetch("http://localhost:8080/auctions/bids/highest/" + id, {
                method: "GET",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
            }).then((response) => response.json())
                .then((data) => {
                    x = data
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
            return x
        },
        blockAuction: async function (id, blocked) {
            let x = "false"
            if (blocked == 0) {
                x = "true";
            } else {
                x = "false";
            }
            console.log(blocked)
            await fetch("http://localhost:8080/auctions/bids/lock/" + id, {
                method: "PATCH",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                body: JSON.stringify(
                    {
                        locked: x,
                    }
                ),
            }).then((response) => response.json())
                .then((data) => {
                    console.log('sucess:', data)
                    location.reload()
                })
                .catch((error) => {
                    console.error('Error:', error)
                });
        },
    },
    beforeMount() {
        this.username = localStorage.getItem("username")
        this.roleCheck = localStorage.getItem("role")
        this.roleFunction = localStorage.getItem("rolefunction")

        if (this.roleCheck == 'admin' || this.roleFunction == 'bidder') {
            this.getAuctions()
            this.getUser()
        }
        else {
            this.$router.push({ path: '/' })
        }
    },
}
</script>
<style lang="">
</style>