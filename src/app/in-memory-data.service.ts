import { InMemoryDbService } from 'angular-in-memory-web-api';

export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const heroes = [
      { id: 1, name: 'Son Tung MTP' },
      { id: 2, name: 'Ho Ngoc Ha' },
      { id: 3, name: 'My Tam' },
      { id: 4, name: 'Noo Phuoc Thinh' },
      { id: 5, name: 'Toc Tien' },
      { id: 6, name: 'Ho Quang Hieu' },
      { id: 7, name: 'Chien Thang' },
      { id: 8, name: 'Hoa Vinh ETN' },
      { id: 9, name: 'The Men' },
      { id: 10, name: 'Bui Anh Tuan' }
    ];
    return {heroes};
  }
}
