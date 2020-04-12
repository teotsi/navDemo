import json
import random
from time import sleep

from pip._vendor import requests


def gen_random_distance():
    return random.uniform(-10, 10) / 111_111, random.uniform(-10, 10) / 111_111


with open("asoee.geojson", 'rb') as file:
    data = json.load(file)
    data = data['features']
    points = set()
    while True:
        entry = random.choice(data)['geometry']
        type = entry['type']
        if type == 'Point' or type == 'Polygon':
            if type == 'Point':
                lon, lat = entry['coordinates']
            elif type == 'Polygon':
                lon, lat = random.choice(random.choice(entry['coordinates']))
            if (lon,lat) not in points:
                print(f'new location! {lat},{lon}')
                points.update((lon,lat))
                pic_lat, pic_lon = gen_random_distance()
                print(f'{pic_lat},{pic_lon}')
                coordinates = {'lat': lat,
                               'lon': lon,
                               'picLat': pic_lat,
                               'picLon': pic_lon}
                print(coordinates)
                response = requests.post(url='http://localhost:8080/nav', json=coordinates)
                pretty_json = json.loads(response.text)
                print(json.dumps(pretty_json, indent=2))
                sleep(0.5)
