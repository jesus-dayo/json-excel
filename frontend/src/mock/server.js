import { createServer, Model } from "miragejs";

export function makeServer({ environment = "test" } = {}) {
  let server = createServer({
    environment,
    models: {
      template: Model,
    },
    routes() {
      this.namespace = "api";
      this.post("/template", (schema, request) => {
        let template = JSON.parse(request.requestBody);
        return server.create("template", {
          ...template,
          createdDate: new Date(),
        });
      });
      this.get("/template", (schema) => {
        return schema.templates.all();
      });
    },
  });
  return server;
}
