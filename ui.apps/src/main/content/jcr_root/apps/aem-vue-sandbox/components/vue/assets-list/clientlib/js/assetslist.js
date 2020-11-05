
const assetslist = {
	name: 'assetslist',
	component: {
		props: ['assets-path'],
		template: `<ul id="assetslist">
						<li v-for="asset in assets">
							<a :href="asset.assetPath">
					{{ asset.assetName }}
					</a>
				</li>
			</ul>`,
		computed: {
			assets() {
				return this.$store.state.assets.assets
			}
		},
		created() {
			this.$store.dispatch('getAssets','/content/dam/aem-vue-sandbox');
		}
	} 
}

export default assetslist

