<template>
    <div class="relative h-96 w-full flex bids-center justify-center">
        <div class="absolute transform left-1/2 -translate-x-1/2 w-96 h-full">
            <div class="relative text-center space-y-6 flex flex-col bids-center">
                <h2 class="text-6xl font-serif text-gray-700 z-50">
                    My Bids
                </h2>
                <table class="min-w-full divide-y divide-gray-200">
                    <thead class="bg-gray-50">
                        <th>
                            <tr>
                                deliverydate
                            </tr>
                        </th>
                        <th>
                            <tr>
                                price
                            </tr>
                        </th>
                        <th>
                            <tr>
                                quantity
                            </tr>
                        </th>
                        <th>
                            <tr>
                                accepted
                            </tr>
                        </th>
                    </thead>
                    <tbody class="divide-y divide-gray-200">
                        <tr v-for="item in bids" :key="item.id">
                            <td>
                                {{ item.deliverydate }}
                            </td>
                            <td>
                                {{ item.price }}
                            </td>
                            <td>
                                {{ item.quantity }}
                            </td>
                            <td>
                                {{ item.accepted }}
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
            username:'',
            roleFunction: ''
        }
    },
    methods: {
        getBids: async function () {
            await fetch("http://localhost:8080/bids/" + this.username, {
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
    },
    beforeMount() {
        this.username = localStorage.getItem("username")
        this.roleCheck = localStorage.getItem("role")
        this.roleFunction = localStorage.getItem("rolefunction")

        if (this.roleCheck == 'admin' || this.roleFunction == 'bidder') {
            this.getBids()
        }
        else {
            this.$router.push({ path: '/' })
        }
    },
}
</script>
<style lang="">
</style>