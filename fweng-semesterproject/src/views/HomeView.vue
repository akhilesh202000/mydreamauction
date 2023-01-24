<template>
   <div class="relative h-96 w-full flex items-center justify-center">
      <div class="absolute transform left-1/2 -translate-x-1/2 w-96 h-full">
         <div class="relative text-center space-y-6 flex flex-col items-center">
            <h2 class="text-6xl font-serif text-gray-700 z-50">
               Home
            </h2>
            <table class="min-w-full divide-y divide-gray-200">
               <thead class="bg-gray-50">
                  <th>
                     <tr>
                        auction
                     </tr>
                  </th>
                  <th>
                     <tr>
                        category
                     </tr>
                  </th>
                  <th>
                     <tr>
                        start date
                     </tr>
                  </th>
               </thead>
               <tbody class="divide-y divide-gray-200">
                  <tr v-for="item in items" :key="item.message">
                     <td>
                        {{ item.name }}
                     </td>
                     <td>
                        {{ item.category }}
                     </td>
                     <td>
                        {{ item.startDate }}
                     </td>
                  </tr>
               </tbody>
            </table>

            <label for="fil" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Filter category or
               date
            </label>
            <input type="text" id="fil" v-model="fil" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="'food' 'wine' 'shoes'" />

            <input type="date" id="date" v-model="date" class="
         bg-gray-50 border border-gray-300 text-gray-900 
         text-sm rouned-lg focus:ring-blue-500 focus:border-blue-500 
         block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
         dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 
         dark:focus:border-blue-500" placeholder="'food' 'wine' 'shoes'" />
            <table class="min-w-full divide-y divide-gray-200">
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
               </thead>
               <tbody class="divide-y divide-gray-200">
                  <tr v-for="item in filter" :key="item.message">
                     <td>
                        {{ item.name }}
                     </td>
                     <td>
                        {{ item.category }}
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
   name: 'HomeView',
   data() {
      return {
         items: [
         ],
         products: [],
         fil: '',
         date:''
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
      }
   },
   computed: {
      filter() {
         return this.items.filter(item => {
            {
               if (item.category == this.fil) {
                  return item.category == this.fil
               } else if (item.startDate == this.date) {
                  return item.startDate == this.date
               }
            }
         })
      },
   },
   beforeMount() {
      this.getAuctions()
   },
}
</script>
<style lang="">
</style>