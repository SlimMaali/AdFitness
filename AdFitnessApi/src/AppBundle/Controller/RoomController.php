<?php

namespace AppBundle\Controller;
use AppBundle\Entity\Room;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;


class RoomController extends Controller
{
    public function createAction(Request $request)
    {
        $room = new Room();
        $em = $this->getDoctrine()->getManager();
        $room->setName($request->get('name'));
        $room->setDescription($request->get('description'));
        $room->setImage($request->get('image'));
        $em->persist($room);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formated = $serializer->normalize($room);
        return new JsonResponse($formated);
    }
    public function updateAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $room= $em->getRepository('AppBundle:Room')->find($request->get('id'));
        $room->setName($request->get('name'));
        $room->setDescription($request->get('description'));
        $room->setImage($request->get('image'));
        $em->persist($room);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formated = $serializer->normalize($room);
        return new JsonResponse($formated);
    }
    public function readAction(Request $request)
    {
        $room=$this->getDoctrine()->getManager()
            ->getRepository('AppBundle:Room')->find($request->get('id'));
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formated=$serializer->normalize($room);
        return new JsonResponse($formated);
    }
    public function readAllAction(Request $request)
    {
        $room=$this->getDoctrine()->getManager()
            ->getRepository('AppBundle:Room')->findAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formated=$serializer->normalize($room);
        return new JsonResponse($formated);
    }
    public function deleteAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $room=$em->getRepository('AppBundle:Room')->find($request->get('id'));
        $em->remove($room);
        $em->flush();
    }
}
