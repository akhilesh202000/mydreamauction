<template>
   <div class="relative h-96 w-full flex items-center justify-center">
      <div class="absolute transform left-1/2 -translate-x-1/2 w-96 h-full">
         <div class="relative text-center space-y-6 flex flex-col items-center">
            <h2 class="text-6xl font-serif text-gray-700 z-50">
               Home
            </h2>
            <table v-if="isblocked != 'locked'" class="min-w-full divide-y divide-gray-200">
               <thead class="bg-gray-50">
                  <th>
                     <tr>
                        id
                     </tr>
                  </th>
                  <th>
                     <tr>
                        auction
                     </tr>
                  </th>
                  <th>
                     <tr>
                        products
                     </tr>
                  </th>
               </thead>
               <tbody class="divide-y divide-gray-200">
                  <tr v-for="item in items" :key="item.message">
                     <td>
                        {{ item.id }}
                     </td>
                     <td>
                        {{ item.name }}
                     </td>
                     <td>
                        {{ item.products[0].name }}
                     </td>
                  </tr>
               </tbody>
            </table>
            <p v-else>No rights</p>
         </div>
      </div>
   </div>
</template>
<script>

export default {
   name: 'HomeView',
   data() {
      return {
         items: [
         ],
         isblocked: ''
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
                     if (data[i].status != "locked") {
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
      }
   },
   beforeMount() {
      this.getAuctions()
   },
}
</script>
<style lang="">
</style>