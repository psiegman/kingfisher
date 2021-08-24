<template>
<div>
<ul class="breadcrumbs">
  <li class="breadcrumb">
    <router-link :to="'/content/schema/' + schema.key">{{ schema.key }}</router-link>
  </li>
</ul>
<div v-if="schema">
<h1>The catalogs of {{ schema.name }}</h1>
<ul>
  <li v-for="catalog in schema.catalogs" v-if="schema" :key="catalog.key">{{ catalog.key}}
    <ul>
      <li v-for="catalogVersion in catalog.catalogVersions" :key="catalogVersion.key">
              <router-link :to="'/content/contentitem/' + schema.key + '/' + catalog.key + '/' + catalogVersion.key">{{ catalogVersion.key }}</router-link>
      </li>
    </ul>
  </li>
</ul>
</div>
</div>
</template>

<script>
import { CATALOG_LIST_QUERY } from "../constants/graphql";

export default {
  name: "CatalogList",
  props: ["schemaKey"],
  data() {
    return {
      schema: null,
      loading: 0
    };
  },
  apollo: {
    schema: {
      query: CATALOG_LIST_QUERY,
      variables() {
        return {
          schemaKey: this.schemaKey
        };
      },
      update: data => {
        return data.cms.cmsContent.schema;
      }
    }
  }
};
</script>
