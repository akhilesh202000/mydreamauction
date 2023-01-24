<template>
    <div class="relative h-96 w-full flex bids-center justify-center">
        <div class="absolute transform left-1/2 -translate-x-1/2 w-96 h-full">
            <div class="relative text-center space-y-6 flex flex-col bids-center">
                <h2 class="text-6xl font-serif text-gray-700 z-50">
                    My Offers
                </h2>
                <table class="min-w-full divide-y divide-gray-200">
                    <thead class="bg-gray-50">
                        <th>
                            <tr>
                                Auction
                            </tr>
                        </th>
                        <th>
                            <tr>
                                products bid
                            </tr>
                        </th>
                    </thead>
                    <tbody class="divide-y divide-gray-200">
                        <tr v-for="(item) in bids" :key="item.id">
                            <td>
                                {{ item.name }}
                            </td>
                            <td v-for="products in item.products" :key="products.id">
                        <tr>
                            <template v-for="bids in products.bids" :key="bids.id">
                                <td v-if="bids.accepted!=1">
                                Product: {{ products.name }} <br>
                                Deliverydate: {{ bids.deliverydate }}<br>
                                Price: {{ bids.price }}<br>
                                Quantity: {{ bids.quantity }}<br>
                        <tr>
                            <button @click="confirm(bids.id, item.id)"
                                class="bg-green-400 mr-5 mt-5 text-white px-4 py-2 rounded">confirm</button>
                        </tr>

                        <tr>
                            <button @click="cancel(bids.id)"
                                class="bg-red-400 mr-5 mt-5 text-white px-4 py-2 rounded">cancel</button>
                        </tr>
                    </td>
                            </template>
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

export default {
    name: 'MyBidsVue',
    data() {
        return {
            roleCheck: '',
            bids: [
            ],
            username: '',
            roleFunction: ''
        }
    },
    methods: {
        getOfffers: async function () {
            await fetch("http://localhost:8080/auctions/offers/" + this.username, {
                method: "GET",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
            }).then((response) => response.json())
                .then((data) => {
                    console.log(data)
                    for (let i = 0; i < data.length; i++) {
                        this.bids.push(data[i])
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        },
        confirm: async function (id, auctionid) {
            await fetch("http://localhost:8080/bids/" + id, {
                method: "PATCH",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                body: JSON.stringify(
                    {
                        accepted: "true"
                    }
                ),
            }).then((response) => response.json())
                .then((data) => {
                    console.log('sucess:', data)
                    alert("confirmed")
                    this.lockAuction(auctionid)
                })
                .catch((error) => {
                    console.error('Error:', error)
                });
        },
        cancel: async function (id) {
            await fetch("http://localhost:8080/bids/" + id, {
                method: "PATCH",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                body: JSON.stringify(
                    {
                        accepted: "false"
                    }
                ),
            }).then((response) => response.json())
                .then((data) => {
                    console.log('sucess:', data)
                    alert("cancelled")
                })
                .catch((error) => {
                    console.error('Error:', error)
                });
        }, lockAuction: async function (id) {
            await fetch("http://localhost:8080/auctions/bids/lock/" + id, {
                method: "PATCH",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                body: JSON.stringify(
                    {
                        locked: "true"
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
        }
    },
    beforeMount() {
        this.username = localStorage.getItem("username")
        this.roleCheck = localStorage.getItem("role")
        this.roleFunction = localStorage.getItem("rolefunction")

        if (this.roleCheck == 'admin' || this.roleFunction == 'buyer') {
            this.getOfffers()
        }
        else {
            this.$router.push({ path: '/' })
        }
    },
}
</script>
<style lang="">
</style>