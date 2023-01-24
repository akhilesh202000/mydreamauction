<template>
    <div class="relative h-96 w-full flex items-center justify-center">
        <div class="absolute transform left-1/2 -translate-x-1/2 w-96 h-full">
            <div class="relative text-center space-y-6 flex flex-col items-center">
                <h2 class="text-6xl font-serif text-gray-700 z-50">
                    Help
                </h2>

                <h3 class="text-2xl font-serif text-gray-700 z-50">OBSERVER</h3>
                <p class="text-gray-500 text-xl max-w-screen-sm">
                    Als Observer kann ich auctions ansehen und diese filtern
                </p>

                <h3 class="text-2xl font-serif text-gray-700 z-50">BUYER</h3>
                <p class="text-gray-500 text-xl max-w-screen-sm">
                    Als Buyer bin ich in der Lage: auctions zu erstellen, locken/entlocken, löschen.
                    Erhaltenen Angebote einzusehen und diese zu akzeptieren oder zu blockieren. Ebenfalls kann ich
                    mein userprofil berbeiten
                </p>

                <h3 class="text-2xl font-serif text-gray-700 z-50">BIDDER</h3>
                <p class="text-gray-500 text-xl max-w-screen-sm">
                    Als Bidder kann ich meine Bids einsehen und deren status ich kann bids tätigen und mein userprofil
                    berbeiten
                </p>

                <h3 class="text-2xl font-serif text-gray-700 z-50">ADMIN</h3>
                <p class="text-gray-500 text-xl max-w-screen-sm">
                    Als Admin kann man alles machen was ein bidder/buyer und observer machen kann
                </p>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: 'HelpView',
}
</script>
<style lang="">
   
</style>