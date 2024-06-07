import {Category} from "./Category";

export class Movie {
  id?: number;
  title: string;
  price: number;
  categories: Category[];
  productionYear: number;
  description: string;

  constructor(id: number, title: string, price: number, categories: Category[], productionYear: number, description: string) {
    this.id = id;
    this.title = title;
    this.price = price;
    this.categories = categories;
    this.productionYear = productionYear;
    this.description = description;
  }
}
