<template>
    <div class="relative h-96 w-full flex items-center justify-center">
        <div class="absolute transform left-1/2 -translate-x-1/2 w-96 h-full">
            <div class="relative text-center space-y-6 flex flex-col items-center">
                <h2 class="text-6xl font-serif text-gray-700 z-50">
                    Admin
                </h2>
                <table class="min-w-full divide-y divide-gray-200">
                    <thead class="bg-gray-50">
                        <th>
                            <tr>
                                name
                            </tr>
                        </th>
                        <th>
                            <tr>
                                email
                            </tr>
                        </th>
                        <th>
                            <tr>
                                role
                            </tr>
                        </th>
                        <th>
                            <tr>
                                status
                            </tr>
                        </th>
                        <th>
                            <tr>

                            </tr>
                        </th>
                        <th>
                            <tr>

                            </tr>
                        </th>
                    </thead>
                    <tbody class="divide-y divide-gray-200">
                        <tr v-for="item in items" :key="item.username">
                            <td class="tmp">
                                {{ item.username }}
                            </td>
                            <td class="tmp">
                                {{ item.email }}
                            </td>
                            <td class="tmp">
                                {{ item.role }}
                            </td>
                            <td class="tmp">
                                {{ item.status }}
                            </td>
                            <td>
                                <button
                                    class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full"
                                    v-bind:id="item.username"
                                    @click="blockUser(item.username, item.role, item.status, item.email)"
                                    v-bind:value="item.username">
                                    {{
                                        item.status == "locked" ? "activate" : "lock"
                                    }}</button>
                            </td>
                            <td>
                                <button
                                    class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full"
                                    @click="editUser(item.username)">
                                    edit
                                </button>
                            </td>
                            <td>
                                <button
                                    class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full"
                                    @click="deleteUser(item.username)">
                                    delete
                                </button>
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
    name: 'AdminVue',
    data() {
        return {
            items: [
            ]
        }
    },
    methods: {
        getAuctions: async function () {
            await fetch("http://localhost:8080/users", {
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
        blockUser: async function (username, role, status, email) {
            if (status == "locked") {
                status = "active";
            } else {
                status = "locked";
            }
            await fetch("http://localhost:8080/users/admin/" + username, {
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
                    location.reload()
                })
                .catch((error) => {
                    console.error('Error:', error)
                });
        },
        deleteUser: async function (username) {
            await fetch("http://localhost:8080/users/" + username, {
                method: "DELETE",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
            }).then((response) => response.json())
                .then((data) => {
                    console.log('sucess:', data)
                    location.reload()
                })
                .catch((error) => {
                    console.error('Error:', error)
                });
        },
        editUser(username) {
            location.href = "edit?username=" + username
        }
    },
    beforeMount() {
        this.getAuctions()
    },
}
</script>
<style lang="">
</style>