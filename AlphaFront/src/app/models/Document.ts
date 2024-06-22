import { cours } from "./cours";

export enum DocCategory {
    ressource, post 
}
export class Document{
    id:number| undefined;

    category!:DocCategory;

    content!:string;
    createdate!:Date;
    updatedate!:Date;

    cours:cours | undefined;
}