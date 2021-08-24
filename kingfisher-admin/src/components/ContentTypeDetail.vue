<template>
<div>
<ul class="breadcrumbs">
  <li class="breadcrumb">
    <router-link :to="'/content/schema/' + schemaKey">{{ schemaKey }}</router-link>
  </li>
</ul>
<div v-if="schema">
<h1>ContentType {{ schema.contentType.key }}</h1>
Key: {{ schema.contentType.key }}<br/>
Name: {{ schema.contentType.name }}<br/>
<p/>
    <form>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th>Key</th>
            <th>Name</th>
            <th>Type</th>
          </tr>
        </thead>
        <tbody>
       <tr v-for="fieldDefinition in schema.contentType.fieldDefinitions" :key="fieldDefinition.key">
          <td><input type="text" :value="fieldDefinition.key"/></td>
          <td><input type="text" :value="fieldDefinition.name"/></td>
          <td><input type="text" :value="fieldDefinition.fieldType"/></td>
       </tr>
        </tbody>
      </table>
    </form>
</div>
</div>
</template>

<script>
import { CONTENT_TYPE_DETAIL_QUERY } from "../constants/graphql";

export default {
  name: "ContentTypeDetail",
  props: ["schemaKey", "contentTypeKey"],
  data() {
    return {
      schema: null,
    };
  },
  apollo: {
    schema: {
      query: CONTENT_TYPE_DETAIL_QUERY,
      variables() {
        return {
          schemaKey: this.schemaKey,
          contentTypeKey: this.contentTypeKey
        };
      },
      update: data => {
        return data.cms.cmsContent.schema;
      }
    }
  }
};
</script>
