<template>
    <div v-if="roleCheck == 'admin'" class="relative h-96 w-full flex items-center justify-center">
        <div class="absolute transform left-1/2 -translate-x-1/2 w-96 h-full">
            <div class="relative text-center space-y-6 flex flex-col items-center">
                <h2 class="text-6xl font-serif text-gray-700 z-50">
                    User list
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
                                status
                            </tr>
                        </th>
                        <th>
                            <tr>
                                role
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
                                {{ item.locked == 0 ? 'active' : 'locked' }}
                            </td>
                            <td class="tmp">
                                {{ item.roleFunction }}
                            </td>
                            <td>
                                <button
                                    class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full"
                                    v-bind:id="item.username"
                                    @click="blockUser(item.username, item.role, item.locked, item.email, item.roleFunction)"
                                    v-bind:value="item.username">
                                    {{
                                        item.locked == 0 ? "lock" : "activate"
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
                                <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded-full"
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
    <div v-else>
        <h1> Sie sind kein Admin</h1>
    </div>
</template>
<script>

export default {
    name: 'AdminVue',
    data() {
        return {
            roleCheck: '',
            items: [
            ]
        }
    },
    methods: {
        getUser: async function () {
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
                        if (data[i].role != "admin")
                            this.items.push(data[i])
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        },
        blockUser: async function (username, role, locked, email, roleFunction) {
            let x = "false"
            if (locked == 0) {
                x = "true";
            } else {
                x = "false";
            }
            console.log(x)
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
                        locked: x,
                        email: email,
                        roleFunction: roleFunction
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
            }).then((response) => response.status == 200 ? location.reload() : alert("error"))
        },
        editUser(username) {
            location.href = "edit?username=" + username
        }
    },
    beforeMount() {
        this.roleCheck = localStorage.getItem("role")
        if (this.roleCheck == 'admin')
            this.getUser()
        else {
            this.$router.push({ path: '/' })
        }
    },
}
</script>
<style lang="">
</style>