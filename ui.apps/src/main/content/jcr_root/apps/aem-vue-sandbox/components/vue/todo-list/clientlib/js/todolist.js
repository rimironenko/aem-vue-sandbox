
const todolist = {
  name: 'todolist',
  component: {
    props: ['number'],
    template: `<ul id="todolist">
					<li v-for="item in items">
					Edit properties of asset: <b>{{ item.assetName }}</b>
					</li>
				</ul>`,
	computed: {
	  items() {
		return this.$store.state.assets.assets;
	  }
	}
  }
}

export default todolist

